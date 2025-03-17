import HeaderLink from '../HeaderLink'
import Logout from '../Logout'

export default function Header() {

    return (
        <header className="d-flex flex-column bg-dark">

            <nav className="d-flex justify-content-end flex-grow-1">
                <HeaderLink>
                    <Logout />
                </HeaderLink>
            </nav>

        </header>
    );
}