import axios from "axios"

export default function CriarTransacao() {

    axios.post();

    return (
        <div className="container bg-dark text-light">
            <h1 className="text-center">Nova Transação</h1>
            <div className="container mt-5 bg-dark pb-5">

                <label >Valor:</label>
                <input className="form-control"></input>
                <label className="mt-2">Forma Pagamento:</label>
                <input className="form-control"></input>
                <label className="mt-2">Instituição:</label>
                <input className="form-control"></input>
                <button className="btn btn-primary mt-2 col-md-2 text-center">Criar</button>
            </div>
        </div>
    )
}