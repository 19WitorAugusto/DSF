import React from "react";
import Main from '../template/Main'

function Home(props){
   return(
    <Main icon="home" title="Início" 
    subtitle="Desafio">
        <div className='display-4'>Bem vindo!</div>
        <hr/>
    </Main>
   )
}
export default Home