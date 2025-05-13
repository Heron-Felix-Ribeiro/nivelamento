import styles from "./sidebar.module.css"
import MenuItem from "../menuItem";
import Submenu from "../submenu";
import {useState} from "react";

export default function Sidebar() {

    const [sideBarAberta, setSideBarAberta] = useState(false);
    const alternar = () => {
        setSideBarAberta(!sideBarAberta);
    }

    return (
        <div className={`${styles.sidebar} d-flex flex-column vh-100 bg-dark`}>
            <button
                className="btn btn-light m-2"
                onClick={alternar}
                style={{alignSelf: "flex-end"}}
            >
                {sideBarAberta ? "Fechar" : "Abrir"}
            </button>

            <div className="p-3 text-center">
                <img src="https://www.mg.senac.br/programasenacdegratuidade/assets/img/senac_logo_branco.png"
                     className="img-fluid- md-2"
                     style={{maxWidth: "120px"}}
                >
                </img>
            </div>
            <ul className="nav flex-column">
                <MenuItem to="/" label="⌂ Menu"/>
                <Submenu
                    label="Tipos de Despesa"
                    id="submenuDepesas"
                    items={[
                        {to: "/despesas", label: "Listar"},
                        {to: "/criar_tipo_despesa", label: "Criar"}
                    ]}
                />
                <Submenu
                    label="Transações"
                    id="submenuTransacoes"
                    items={[
                        {to: "/transacoes", label: "Listar"},
                        {to: "/criar_transacao", label: "Criar"}
                    ]}
                />
            </ul>
        </div>
    )
}