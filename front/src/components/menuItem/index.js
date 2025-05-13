import {Link} from "react-router-dom";

export default function MenuItem({to, label}) {

    return (
        <li className="nav-item">
            <Link to={to} className="nav-link text-light">
                {label}
            </Link>
        </li>
    )
}