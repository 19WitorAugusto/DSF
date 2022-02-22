import React, { Component } from 'react'
import axios from 'axios'
import Main from '../template/Main'

//informaçoes para header
const headerProps = {
    icon: '',
    title: 'Cadastro de apartamentos',

}
//URL backend
const baseUrl = 'http://localhost:8080/api/apartamento'

//estado inicial, clique em cancelar irá limpar formulario
const initialState = {
    apto: { numero: '', estado: 0 },
    list: []
}

export default class aptoCrud extends Component {

    state = { ...initialState }

    //função chamada quando um componente for ser exibido na tela
    componentDidMount() {
        axios(baseUrl).then(resp => {//realizar get em base url
            this.setState({ list: resp.data })//tras a resposta - o que receber de resposta, salva na lista
        }).catch((err) => console.log('Erro ao buscar apartamentos.', err))
    }

    //limpar formulario - clicar em cancelar ira chamar esta função para limpar o form
    clear() {
        this.setState({ apto: initialState.apto })//limpar o apto
    }

     save() {
        const apto = this.state.apto//obter usuario
        const method = apto.id ? 'put' : 'post'//se tiver id atualiza, se não adiciona novo
        const url = apto.id ? `${baseUrl}/${apto.id}` : baseUrl
         axios[method](url, apto)           
            .then(resp => {               
                const list = this.getUpdatedList(resp.data)//atualizar lista local
                this.setState({ apto: initialState.apto, list })//alterar o apto a partir do initialState.apto , depois que salvar o usuario limpar o formulario e atualizar a lista retornada por (getUpdatedList)

            }).catch(err  => { console.log('Erro ao incluir/atualizar apartamento - campo null.', err); alert("Verifique o campo número!")});
    }

    getUpdatedList(apto) {
        //filter gera uma nova lista, sendo assim não ha necessidade de clonar lista do estado, gerando uma nova
        const list = this.state.list.filter(a => a.id !== apto.id)//
        //list.unshift(apto)//unshift, coloca o elemento na primeira posição do array
        return list
    }

    //função para atualizar campos no formulario
    updateField(event) {
        const apto = { ...this.state.apto }//clonando apto com spread
        apto[event.target.name] = event.target.value//name - utilizando name do input para procurar a propriedade dentro do estado(apto) e pegando o seu valor
        this.setState({ apto })
    }

    //renderização do formulario
    renderForm() {
        return (
            <div className="form">
                <div className="row">
                    <div className="col-12 col-md-6">
                        <div className="form-group">
                            <label>Número</label>
                            <input type='number' className='form-control :invalid' name='numero'//input para campo numero
                                value={this.state.apto.numero}
                                onChange={e => this.updateField(e)} placeholder='Digite o número do apartamento...' />
                        </div>
                    </div>

                    <div className="col-12 col-md-6">
                        {/* input para escolha de estado */}
                        <label>ESTADO</label>

                        <div className="form-group">
                            <input type="radio" className='form-control-iniline mr-2' name='estado'
                                value={0}//valor enumeração estado backend (estado livre)
                                onChange={e => this.updateField(e)} />
                            <label>LIVRE</label>
                        </div>

                        <div className="form-group">
                            <input type="radio" className='form-control-inline mr-2' name='estado'
                                value={1}//valor enumeração estado backend (estado locado)
                                onChange={e => this.updateField(e)} />
                            <label>LOCADO</label>
                        </div>

                    </div>
                </div>


                <hr />
                {/* botões - salvar e cancelar*/}
                <div className="row">
                    <div className="col-12 d-flex justify-content-end">
                        <button className="btn btn-primary"
                            onClick={e => this.save(e)}>
                            Salvar
                        </button>

                        <button className="btn btn-secondary ml-2"
                            onClick={e => this.clear(e)}>
                            Cancelar

                        </button>

                    </div>
                </div>
            </div>
        )
    }

    //atualizar o estado do objeto
    load(apto) {
        this.setState({ apto })
    }

    //função delelar
    remove(apto) {
        axios.delete(`${baseUrl}/${apto.id}`).then(resp => {//metodo, url e id.
            //const list = this.getUpdatedList(null)//atualizar lista para remover da lista
            const list = this.state.list.filter(u => u !== apto)//atualizar lista para remover da lista
            this.setState({ list })
        }).catch(err => { console.log('Erro ao excluir apartamento.', err); alert('Erro desconhecido, entre em contato com o adminstrador!')});
    }

    //renderização da tabela
    renderTable() {
        return (
            <table className="table mt-4">
                <thead>
                    <tr>
                        <th>Número</th>
                        <th>Estado</th>
                        <th>Ações</th>
                    </tr>
                </thead>
                <tbody>
                    {this.renderRows()}
                </tbody>
            </table>
        )
    }

    //renderização de linhas
    renderRows() {
        return this.state.list.map(apto => {//mapear lista de apto que estão dentro do estado do obj para jsx
            
            return (
                <tr key={apto.id}>{/*chave - para que não gere advertencias de chaves*/}
                    <td>{apto.numero}</td>
                    <td>{apto.estado}</td>
                    <td>
                        <button className="btn btn-warning"
                            onClick={() => this.load(apto)}>
                            <i className="fa fa-pencil"></i>
                        </button>
                        <button className="btn btn-danger ml-2"
                            onClick={() => this.remove(apto)}>
                            <i className="fa fa-trash"></i>
                        </button>
                    </td>
                </tr>
            )
        })
    }

    render() {
        return (
            <Main {...headerProps}>
                {this.renderForm()}
                {this.renderTable()}
            </Main>
        )
    }
}