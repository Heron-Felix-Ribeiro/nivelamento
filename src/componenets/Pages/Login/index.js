import Footer from "../../Footer";
import style from './login.module.css'

export default function Login() {

    return (
        <div>
            <h1>Página de Login</h1>
                <div>
                    <form className={style.form}> 
                        <label>Usuario: </label>
                        <input></input>
                        <label>Senha: </label>
                        <input></input>
                    </form>
                </div>
            <Footer/>
        </div>
    )
}