export default function BotaoExcluir({ id, aoExcluir }) {
    const handleExcluir = () => {
        aoExcluir(id);
    };

    return (
        <button
            className="btn btn-danger"
            onClick={handleExcluir}
        >
            Deletar 
        </button>
    );
}