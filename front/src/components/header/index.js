import HeaderLink from '../headerLink';
import Logout from '../logout';

export default function Header() {
    return (
        <header className="d-flex flex-column bg-dark py-4">
            <div className="d-flex align-items-center justify-content-between flex-grow-1">
                <h1 className="text-light text-center w-100 mb-0">Sistema Financeiro</h1>
                <nav>
                    <HeaderLink>
                        <Logout/>
                    </HeaderLink>
                </nav>
            </div>
        </header>
    );
}