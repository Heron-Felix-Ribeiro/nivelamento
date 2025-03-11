import { Link } from "react-router-dom";
import styles from "./sidebar.module.css"
import MenuItem from "../MenuItem";
import Submenu from "../Submenu";

export default function Sidebar() {

    return (
        <div className={`${styles.sidebar} d-flex flex-column vh-100 bg-dark`}>

            <div className="p-3 text-center">
                <img src="data:image/png;base64,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"
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
                    ]}
                />
                <Submenu 
                label="Transações"
                id="submenuTransacoes"
                items={[
                    {to: "/transacoes", label: "Lista"}, 
                    {to: "/criar_transacao", label: "Cadastrar"}
                ]}
                />
            </ul>
        </div>
    )
}