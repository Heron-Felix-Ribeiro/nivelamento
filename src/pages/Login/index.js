import Footer from "../../components/Footer";

export default function Login() {

    return (
        <div>
            <h1 className="container text-center fw-bold mt-3">Página de Login</h1>
            <div className="md-6 sm-12 text-center">
                <label className="md-6 m-1">Usuario: </label>
                <input className="md-10 m-1"></input>
                <label className="md-6 m-1">Senha: </label>
                <input className="md-10 m-1"></input>
            </div>
        </div>
    )
}