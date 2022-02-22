import './App.css'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'font-awesome/css/font-awesome.min.css'
import {BrowserRouter} from 'react-router-dom'
import Routes from './Routes'
import React from 'react'

import Nav from '../components/template/Nav'
import Footer from '../components/template/Footer'
import Main from '../components/template/Main'


function App (props) {    
    return(
        <BrowserRouter>
            <div className='app'>
              
                <Nav/>
                <Main icon="home" title="Início">
                    <div className='display-4'>Bem Vindo!</div>
                    </Main>                    
                <Routes/>
                <Footer/>
            </div>

        </BrowserRouter>      
    )
}
export default App