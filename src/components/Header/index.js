import { Button } from 'bootstrap'
import HeaderLink from '../HeaderLink'
import style from './header.module.css'
import Logout from '../Logout'

export default function Header() {



    return (
        <header className={`${style.header} bg-dark`}>
            
                <nav>
                    <HeaderLink>
                        <div>
                            <Logout />
                        </div>

                    </HeaderLink>
                </nav>
            
        </header>
    )
}