//funções de login

function validaLogin() {

    let usertxt = localStorage.getItem("userlogged");
    if(!usertxt){
        window.location ="index.html"
    }
    
    let user = JSON.parse(usertxt);

    document.getElementById("user").innerHTML = `Nome: ${user.nome}               <br> RACF: ${user.racf} `
    document.getElementById("foto").innerHTML = `<img src=${user.linkFoto}>`

}

function logout() {
    localStorage.removeItem("userlogged");
    window.location ="index.html"
    
}

//funcoes de relatorio de alarme

function gerarRelatorioAlarme() {

    fetch("http://localhost:8080/alarmes/all")
        .then(res => tratarResposta(res))
        
    
}

function tratarResposta(resposta){
    if(resposta){
        resposta.json().then(res => exibirDadosAlarme(res))
    }


}


function exibirDadosAlarme(listaAlarmes){
    
    let tabela = '<table class="table table-sm titulo-tabela border-black"> <tr class="table-dark font"> <th>Nome</th> <th>Descricao</th> </tr>'
    for(i=0; i< listaAlarmes.length; i++){
        tabela = tabela +`<tr class="border-black"> <td>${listaAlarmes[i].nome} </td> <td> ${listaAlarmes[i].descricao} </td>   </tr>`

    }

        tabela = tabela + '</table>'
        document.getElementById("tabela").innerHTML = tabela
}


// funcoes de relatorio de evento

function gerarRelatorioEventos() {

    let dataInicio = document.getElementById("dataInicio").value
    let dataFinal = document.getElementById("dataFinal").value

    let datas = {
        dataIni:dataInicio,
        dataFim:dataFinal
    }

    let msg = {
        method: 'POST',
        body: JSON.stringify(datas),
        headers:{
            'Content-type':'application/json'
        }
    }


    fetch("http://localhost:8080/evento/data", msg)
        .then(res => tratarRespostaEvento(res))
        
    
}

function tratarRespostaEvento(resposta){
    if(resposta){
        resposta.json().then(res => exibirDadosEvento(res))
    }


}


function exibirDadosEvento(listaEventos){
    console.log(listaEventos);
     let tabela = '<table class="table table-sm font border-black"> <tr class="table-dark font"> <th>Data</th> <th>Alarme</th> <th> Equipamento </th> </tr>'
    for(i=0; i< listaEventos.length; i++){
        let databr= new Date(listaEventos[i].dataEvt).toLocaleDateString("pt-BR", {timeZone:'UTC'});
        tabela = tabela +`<tr class="border-black"> <td>${databr} </td> <td> ${listaEventos[i].alarme.nome} </td> <td> ${listaEventos[i].equipamento.hostname} </td> </tr>`

    }

        tabela = tabela + '</table>'
        document.getElementById("tabela").innerHTML = tabela 
} 