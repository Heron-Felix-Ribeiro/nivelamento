import style from './HeaderLink.module.css'

export default function HeaderLink (url, children) {

    return (
        <href url = {url} className={style.link}>
            {children}
        </href>
          
        )
}

     