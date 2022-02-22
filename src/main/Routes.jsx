import React from "react";
import {Routes, Route} from 'react-router-dom'
import Home from '../components/home/Home'

import ApartamentoCrud from "../components/apartamento/apartamentoCrud";

export default props => (
    <Routes>
        <Route exact path='/home' element={<Home/>}/>
        <Route path='api/apartamento' element={<ApartamentoCrud/>}/>
        {/* <Route path='*' element={<Home/>}/> */}
        
    </Routes>
)