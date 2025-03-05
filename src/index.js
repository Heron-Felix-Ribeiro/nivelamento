import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import Header from './componenets/Header';
import Footer from './componenets/Footer'; 
import Despesas from './componenets/Pages/Despesas';
import Login from './componenets/Pages/Login';
import Menu from './componenets/Pages/Menu';
import Relatorios from './componenets/Pages/Relatorios';
import Transacoes from './componenets/Pages/Transacoes';

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <Login/>
  </React.StrictMode>
);


