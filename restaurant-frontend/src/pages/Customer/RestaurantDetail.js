import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import { getRestaurantById } from '../../api/restaurantApi';
import { getMenuItems } from '../../api/menuApi';
import MenuItemCard from '../../components/customer/MenuItemCard';
import LoadingSpinner from '../../components/common/LoadingSpinner';

const RestaurantDetail = () => {
    const { id } = useParams();
    const [restaurant, setRestaurant] = useState(null);
    const [menuItems, setMenuItems] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchData = async () => {
            try {
                const [restaurantData, menuData] = await Promise.all([
                    getRestaurantById(id),
                    getMenuItems(id)
                ]);
                setRestaurant(restaurantData);
                setMenuItems(menuData);
            } catch (err) {
                setError(err.message);
            } finally {
                setLoading(false);
            }
        };
        fetchData();
    }, [id]);

    if (loading) return <LoadingSpinner />;
    if (error) return <div className="text-red-500 text-center mt-8">Error: {error}</div>;

    return (
        <div className="container mx-auto px-4 py-8">
            <div className="bg-white rounded-lg shadow-md p-6 mb-8">
                <div className="flex justify-between items-center mb-4">
                    <h1 className="text-2xl font-bold">{restaurant.name}</h1>
                    <span className={`px-3 py-1 rounded-full text-sm ${
                        restaurant.available ? 'bg-green-100 text-green-800' : 'bg-red-100 text-red-800'
                    }`}>
            {restaurant.available ? 'Open' : 'Closed'}
          </span>
                </div>
                <p className="text-gray-600 mb-2">{restaurant.address}</p>
                <p className="text-gray-700">{restaurant.description}</p>
            </div>

            <h2 className="text-xl font-semibold mb-4">Menu</h2>
            <div className="space-y-4">
                {menuItems.length === 0 ? (
                    <p className="text-gray-500">No menu items available</p>
                ) : (
                    menuItems.map((item) => (
                        <MenuItemCard key={item.id} item={item} />
                    ))
                )}
            </div>
        </div>
    );
};

export default RestaurantDetail;