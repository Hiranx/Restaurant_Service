import React from 'react';
import { Link } from 'react-router-dom';

const RestaurantCard = ({ restaurant }) => {
    return (
        <div className="bg-white rounded-lg shadow-md overflow-hidden hover:shadow-lg transition-shadow duration-300">
            <div className="p-6">
                <h3 className="text-xl font-semibold text-gray-800 mb-2">{restaurant.name}</h3>
                <p className="text-gray-600 mb-4">{restaurant.address}</p>
                <div className="flex justify-between items-center">
                    <span className={`px-3 py-1 rounded-full text-sm ${
                        restaurant?.available ? 'bg-green-100 text-green-800' : 'bg-red-100 text-red-800'
                    }`}>
                        {restaurant?.available ? 'Open' : 'Closed'}
                    </span>
                    <Link
                        to={`/restaurants/${restaurant.id}`}
                        className="text-blue-600 hover:text-blue-800 font-medium"
                    >
                        View Menu →
                    </Link>
                </div>
            </div>
        </div>
    );
};

export default RestaurantCard;