import styles from "./sidebar.module.css"
import MenuItem from "../menuItem";
import Submenu from "../submenu";
import {useSelector} from "react-redux";

export default function Sidebar() {
    const cnpjExiste = useSelector((state) => state.auth.cnpj);
    const cnpj = cnpjExiste
    return (
        <div className={`${styles.sidebar} d-flex flex-column min-vh-100 bg-dark`}>

            <div className="p-3 text-center">
                <img src="https://www.mg.senac.br/programasenacdegratuidade/assets/img/senac_logo_branco.png"
                     className="img-fluid- md-2"
                     style={{maxWidth: "120px"}}
                >
                </img>
            </div>
            <ul className="nav flex-column">
                {!cnpj && (
                    <>
                        <MenuItem to="/dashboard" label="⌂ Dashboard" />
                        <MenuItem to="/empresa" label="🏢 Empresa" />
                        <MenuItem to="/inativos" label="Inativos" />
                    </>
                )}
                {cnpj && (
                    <>
                        <MenuItem to="/" label="⌂ Home" />
                        <Submenu
                            label="Tipos de Despesa"
                            id="submenuDepesas"
                            items={[
                                { to: "/despesas", label: "Listar" },
                                { to: "/criar_tipo_despesa", label: "Criar" },
                            ]}
                        />
                        <Submenu
                            label="Transações"
                            id="submenuTransacoes"
                            items={[
                                { to: "/transacoes", label: "Listar" },
                                { to: "/criar_transacao", label: "Criar" },
                            ]}
                        />
                    </>
                )}
            </ul>
        </div>
    )
}