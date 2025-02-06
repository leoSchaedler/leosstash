pragma solidity ^0.4.25; // 

contract Crowdfunding {
    struct Saque {
        string descricao;
        uint valor;
        address destinatario;
        bool finalizado;
        uint numeroAprovados;
        mapping (address => bool) approvals;
    }

    Saque[] public saques;
    address public owner;
    mapping(address => bool) contributors;
    uint public numero_contribuidores;
    uint public minima_contribuicao;
    uint public total_contribuicao;

    constructor(uint minimun, address creator) public {
        owner = creator;
        minima_contribuicao = minimun;
        total_contribuicao = 0;
    }

    function contribuir() public payable{
        uint valor = msg.value / 1000000000000000000;
        require(valor >= minima_contribuicao);
        contributors[msg.sender] = true;
        numero_contribuidores++;
        total_contribuicao += valor;
    }

    modifier onlyOwner() {
        require(msg.sender == owner);
        _;
    }

    modifier onlyContributer() {
        require(contributors[msg.sender]);
        _;
    }

    function criarSaque(string descricao, uint valor, address destinatario) public onlyOwner {
        require(valor <= total_contribuicao);
        Saque memory novoSaque = Saque({
            descricao: descricao,
            valor: valor,
            destinatario: destinatario,
            finalizado: false,
            numeroAprovados: 0
        });
        saques.push(novoSaque);

    }

    function aprovarSaque(uint index) public onlyContributer {
        Saque storage saque = saques[index];
        
        require(!saque.approvals[msg.sender]);

        saque.approvals[msg.sender] = true;
        saque.numeroAprovados++;
    }

    function finalizarSaque(uint index) public onlyOwner {
        Saque storage saque = saques[index];
        require(saque.numeroAprovados > (numero_contribuidores / 2));
        require(!saque.finalizado);
        total_contribuicao -= saque.valor;
        saque.destinatario.transfer(saque.valor);
        saque.finalizado = true;
    }
}