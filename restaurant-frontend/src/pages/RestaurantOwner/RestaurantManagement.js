import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import { getRestaurantById, updateRestaurant, toggleAvailability } from '../../api/restaurantApi';
import { getMenuItems, addMenuItem, updateMenuItem, deleteMenuItem, toggleItemAvailability } from '../../api/menuApi';
import MenuItemForm from '../../components/restaurant/MenuItemForm';
import LoadingSpinner from '../../components/common/LoadingSpinner';

const RestaurantManagement = () => {
    const { id } = useParams();
    const [restaurant, setRestaurant] = useState(null);
    const [menuItems, setMenuItems] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [isEditing, setIsEditing] = useState(false);
    const [formData, setFormData] = useState({});
    const [isAddingItem, setIsAddingItem] = useState(false);
    const [editingItem, setEditingItem] = useState(null);
    const [menuItemsLoading, setMenuItemsLoading] = useState(true);
    const [menuItemsError, setMenuItemsError] = useState(null);

    useEffect(() => {
        let isMounted = true;

        const fetchData = async () => {
            try {
                // First fetch restaurant data
                const restaurantData = await getRestaurantById(id);
                if (isMounted) {
                    setRestaurant(restaurantData);
                    setFormData(restaurantData);
                }

                // Then fetch menu items separately
                try {
                    const menuData = await getMenuItems(id);
                    if (isMounted) setMenuItems(menuData);
                } catch (menuError) {
                    if (isMounted) setMenuItemsError(menuError.message);
                    if (isMounted) setMenuItems([]); // Set empty array if menu fails
                } finally {
                    if (isMounted) setMenuItemsLoading(false);
                }

            } catch (err) {
                if (isMounted) setError(err.message);
            } finally {
                if (isMounted) setLoading(false);
            }
        };

        fetchData();

        return () => {
            isMounted = false;
        };
    }, [id]);

    // ... (keep all your existing handler functions unchanged)

    if (loading) return <LoadingSpinner />;
    if (error) return <div className="text-red-500 text-center mt-8">Error loading restaurant: {error}</div>;
    if (!restaurant) return <div className="text-center mt-8">Restaurant not found</div>;

    return (
        <div className="container mx-auto px-4 py-8">
            {/* Restaurant header with toggle button */}
            <div className="flex justify-between items-center mb-6">
                <h1 className="text-2xl font-bold">{restaurant.name}</h1>
                <button
                    onClick={handleToggleAvailability}
                    className={`px-4 py-2 rounded ${
                        restaurant.available ? 'bg-green-500 hover:bg-green-600' : 'bg-red-500 hover:bg-red-600'
                    } text-white`}
                >
                    {restaurant.available ? 'Open' : 'Closed'}
                </button>
            </div>

            {/* Restaurant details section */}
            {isEditing ? (
                <form onSubmit={handleUpdate} className="bg-white p-6 rounded-lg shadow-md mb-8">
                    {/* ... keep existing form fields ... */}
                </form>
            ) : (
                <div className="bg-white p-6 rounded-lg shadow-md mb-8">
                    <div className="flex justify-between mb-4">
                        <h2 className="text-xl font-semibold">Restaurant Details</h2>
                        <button
                            onClick={() => setIsEditing(true)}
                            className="px-4 py-2 bg-blue-500 hover:bg-blue-600 text-white rounded"
                        >
                            Edit
                        </button>
                    </div>
                    <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
                        <div>
                            <p className="text-gray-600">Name: {restaurant.name}</p>
                            <p className="text-gray-600">Address: {restaurant.address}</p>
                            <p className="text-gray-600">Status: {restaurant.available ? 'Open' : 'Closed'}</p>
                        </div>
                        <div>
                            <p className="text-gray-600">Description: {restaurant.description}</p>
                        </div>
                    </div>
                </div>
            )}

            {/* Menu items section */}
            <div className="flex justify-between items-center mb-4">
                <h2 className="text-xl font-semibold">Menu Items</h2>
                <button
                    onClick={() => {
                        setIsAddingItem(true);
                        setEditingItem(null);
                    }}
                    className="px-4 py-2 bg-green-500 hover:bg-green-600 text-white rounded"
                >
                    Add Item
                </button>
            </div>

            {/* Menu item form */}
            {(isAddingItem || editingItem) && (
                <MenuItemForm
                    initialData={editingItem || {}}
                    onSubmit={editingItem ? handleUpdateItem : handleAddItem}
                    onCancel={() => {
                        setIsAddingItem(false);
                        setEditingItem(null);
                    }}
                />
            )}

            {/* Menu items list */}
            {menuItemsLoading ? (
                <LoadingSpinner />
            ) : menuItemsError ? (
                <div className="bg-yellow-50 border-l-4 border-yellow-400 p-4 mb-4">
                    <div className="flex">
                        <div className="flex-shrink-0">
                            <svg className="h-5 w-5 text-yellow-400" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20" fill="currentColor">
                                <path fillRule="evenodd" d="M8.257 3.099c.765-1.36 2.722-1.36 3.486 0l5.58 9.92c.75 1.334-.213 2.98-1.742 2.98H4.42c-1.53 0-2.493-1.646-1.743-2.98l5.58-9.92zM11 13a1 1 0 11-2 0 1 1 0 012 0zm-1-8a1 1 0 00-1 1v3a1 1 0 002 0V6a1 1 0 00-1-1z" clipRule="evenodd" />
                            </svg>
                        </div>
                        <div className="ml-3">
                            <p className="text-sm text-yellow-700">
                                Couldn't load menu items: {menuItemsError}. <button
                                onClick={() => window.location.reload()}
                                className="text-yellow-700 underline"
                            >
                                Try again
                            </button>
                            </p>
                        </div>
                    </div>
                </div>
            ) : menuItems.length === 0 ? (
                <div className="bg-white p-6 rounded-lg shadow-md text-center">
                    <p className="text-gray-500">This restaurant currently has no menu items</p>
                    <button
                        onClick={() => setIsAddingItem(true)}
                        className="mt-2 px-4 py-2 bg-blue-500 hover:bg-blue-600 text-white rounded"
                    >
                        Add Your First Menu Item
                    </button>
                </div>
            ) : (
                <div className="bg-white shadow-md rounded-lg overflow-hidden">
                    <table className="min-w-full divide-y divide-gray-200">
                        {/* ... keep existing table structure ... */}
                    </table>
                </div>
            )}
        </div>
    );
};

export default RestaurantManagement;