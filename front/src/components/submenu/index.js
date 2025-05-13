import {Link} from "react-router-dom";

export default function Submenu({label, id, items}) {

    return (
        <li className="nav-item">
            <a
                className="nav-link text-light"
                data-bs-toggle="collapse"
                href={`#${id}`}
                role="button"
                aria-expanded="false"
                aria-controls={id}
            >
                {label}
            </a>
            <ul id={id} className="collapse list-unstyled ms-3">
                {items.map((item, index) => (
                    <li key={index}>
                        <Link to={item.to} className="nav-link text-light">
                            {item.label}
                        </Link>
                    </li>
                ))}
            </ul>
        </li>
    )
}