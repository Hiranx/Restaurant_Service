import React from 'react';

const MenuItemCard = ({ item }) => {
    return (
        <div className="bg-white rounded-lg shadow p-4 mb-4">
            <div className="flex justify-between">
                <div>
                    <h4 className="font-semibold text-lg">{item.name}</h4>
                    <p className="text-gray-600 text-sm">{item.description}</p>
                </div>
                <div className="text-right">
                    <span className="font-bold">${item.price.toFixed(2)}</span>
                </div>
            </div>
        </div>
    );
};

export default MenuItemCard;