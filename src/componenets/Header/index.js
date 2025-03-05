import HeaderLink from '../HeaderLink'
import style from './header.module.css'

export default function Header() {



    return (
        <header className={style.header}>
            <nav>
            {/*<HeaderLink url="./">
                    Home
                </HeaderLink>
                <HeaderLink url="./transacoes">
                    Transações
                </HeaderLink>
                <HeaderLink url = "./tiposDespesa">
                    Tipos de Despesa 
                </HeaderLink>*/}
                <h1> Ola </h1>
            </nav>
        </header>
    )
}