import {UsuarioContext, useUsuarioContext} from "../../contexts/Usuario";
import {useDispatch} from "react-redux";
import {logout} from "../../redux/authSlice";
import {limparInfo} from "../../redux/infoSlice";

export default function () {
    const dispatch = useDispatch();

    const handleLogout = () => {

        dispatch(logout());
        dispatch(limparInfo);
    };

    return (

        <button type="button" className="btn btn-danger" onClick={handleLogout}>
            Logout
        </button>

    )
}