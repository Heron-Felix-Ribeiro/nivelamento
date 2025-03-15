import axios from "axios"


export default function Transacoes() {

    axios.get()

    return (
        <div>
            <h1 className="md-12 mt-3 text-center fw-bold"> Página de transações</h1>
            <table className="table table-striped table-bordered">
                <thead className="table-dark">
                    <tr>
                        <th>Valor</th>
                        <th>Parcelas</th>
                        <th>Estabelecimento</th>
                        <th>Data</th>
                    </tr>
                </thead>
            </table>

        </div>
    )
}