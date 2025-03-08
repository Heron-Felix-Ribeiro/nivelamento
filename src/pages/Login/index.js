

export default function Login() {

    return (
        <div className="container md-6 bg-dark text-light">
            <h1 className="text-center fw-bold mt-2">Login</h1>
            <div className="container mt-5 bg-dark pb-5">

                <label className="text-light">Usuario: </label>
                <input className="form-control"></input>
                <label className="text-light">Senha: </label>
                <input className="form-control"></input>
                <button className="btn btn-primary mt-2 col-md-2 text-center">Logar</button>
            </div>
        </div>
    )
}