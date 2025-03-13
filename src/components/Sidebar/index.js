import { Link } from "react-router-dom";
import styles from "./sidebar.module.css"
import MenuItem from "../MenuItem";
import Submenu from "../Submenu";

export default function Sidebar() {

    return (
        <div className={`${styles.sidebar} d-flex flex-column vh-100 bg-dark`}>

            <div className="p-3 text-center">
                <img src= "https://www.mg.senac.br/programasenacdegratuidade/assets/img/senac_logo_branco.png"
                className="img-fluid- md-2"
                    style={{ maxWidth: "120px" }}
                    >
                </img>
            </div>
            <ul className="nav flex-column">
                <MenuItem to="/" label= "⌂ Menu" />
                <Submenu
                    label="💸 Despesas"
                    id="submenuDepesas"
                    items={[
                        { to: "/despesas", label: "Lista" },
                        { to: "/criar_tipo_despesa", label: "Criar Tipo Despesa"}
                    ]}
                />
                <Submenu 
                label="Transações"
                id="submenuTransacoes"
                items={[
                    {to: "/transacoes", label: "Lista"}, 
                    {to: "/criar_transacao", label: "Criar"}
                ]}
                />
            </ul>
        </div>
    )
}