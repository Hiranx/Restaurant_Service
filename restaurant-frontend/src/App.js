import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Layout from './components/common/Layout';
import AdminDashboard from './pages/Admin/AdminDashboard';
import RestaurantList from './pages/Customer/RestaurantList';
import RestaurantDetail from './pages/Customer/RestaurantDetail';
import RestaurantManagement from './pages/RestaurantOwner/RestaurantManagement';


function App() {
    return (

            <Router>
                <Layout>
                    <Routes>
                        <Route path="/" element={<RestaurantList />} />
                        <Route path="/restaurants/:id" element={<RestaurantDetail />} />
                        <Route path="/admin" element={

                                <AdminDashboard />

                        } />
                        <Route path="/manage/:id" element={

                                <RestaurantManagement />

                        } />

                    </Routes>
                </Layout>
            </Router>

    );
}

export default App;