import { UsuarioContext, useUsuarioContext } from "../../contexts/Usuario";

export default function () {

    const { logout } = useUsuarioContext(UsuarioContext);

    return (

        <button type="button" className="btn btn-danger" onClick={(e) => {
            e.preventDefault();
            logout();
        }}>
            Logout
        </button>

    )
}