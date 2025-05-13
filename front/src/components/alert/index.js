export default function Alert({ mensagem, tipo }) {

    return (
        <div className={`alert ${tipo}`} role="alert">
            {mensagem}
        </div>
    );
}