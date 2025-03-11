export default function CriarDespesa () {

    return (
        <div className="container bg-dark text-light">
            <h1 className="text-center">Nova Tipo de Despesa</h1>
            <div className="container mt-5 bg-dark pb-5">

                <label >Nome:</label>
                <input className="form-control"></input>
                <button className="btn btn-primary mt-2 col-md-2 text-center mt-4">Criar</button>
            </div>
        </div>
    )
}