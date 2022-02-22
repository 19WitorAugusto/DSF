import './Nav.css'
import React from 'react'
import { Link } from 'react-router-dom'

function App(props) {
    return (
        <aside className="menu-area">
            <nav className='menu'>
              
                <Link to="/home">
                    <i className='fa fa-home'></i> Início
                </Link>
                <Link to="api/apartamento">
                    <i className='fa fa-bed'></i> Apartamentos
                </Link>
    
            </nav>
        </aside>
    );
}

export default App