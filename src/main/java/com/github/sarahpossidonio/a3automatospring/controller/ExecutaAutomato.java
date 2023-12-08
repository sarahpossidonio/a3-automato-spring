package com.github.sarahpossidonio.a3automatospring.controller;

import org.springframework.web.bind.annotation.RestController;

import com.github.sarahpossidonio.a3automatospring.model.RequestModel;
import com.github.sarahpossidonio.a3automatospring.model.ResponseModel;
import com.github.sarahpossidonio.a3automatospring.util.Automata;
import com.github.sarahpossidonio.a3automatospring.util.State;
import com.github.sarahpossidonio.a3automatospring.util.Transition;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/api")
public class ExecutaAutomato {

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/teste")
    public String ola(){
        return "Tudo Certo";
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/verifica")
    public ResponseEntity<ResponseModel> verificaAutomato(@RequestBody RequestModel body){
        Automata automato = iniciaAutomato();

        Transition transition;
        State destiny;
        int j = 0;
        int origin = 0;
        String symbol = body.getInput();
        ResponseModel res = new ResponseModel();

        for (int i = 0; i <= 4; i++) {
            automato.setState(i);
            automato.getState(i).setName("Q" + i); // Defina o nome do estado
        }
        while (j < symbol.length()) {
            res.setResponse("Entrada: " + symbol);
            if ((symbol.charAt(j) != ' ')) {
                transition = automato.getTransition(origin, "" + symbol.charAt(j));
                if(transition !=null){

                    destiny = transition.getDestiny();
                    origin = destiny.getId();
                    res.setResponse("Leu o simbolo \"" + symbol.charAt(j) + "\" foi para o \""
                            + automato.getState(origin).getName());
                    j++;
                }else{
                    return ResponseEntity.ok(new ResponseModel("Cadeia rejeitada"));
                }
            }else{
                return ResponseEntity.ok(new ResponseModel("Este autômato não aceita transições vazias! "));
            }
        }
        
        if (automato.isFinalState(origin)) {
            return ResponseEntity.ok(new ResponseModel("Cadeia aceita"));
        } else {
            return ResponseEntity.ok(new ResponseModel("Cadeia rejeitada"));
        }

    }

    public Automata iniciaAutomato(){
        Automata automato = new Automata();

        // Definir estados
        automato.setState(0);  // Estado 0
        automato.setState(1);  // Estado 1
        automato.setState(2);  // Estado 2
        automato.setState(3);  // Estado 3
        automato.setState(4);  // Estado 4

        //definir estado inicial
        automato.setStartState(0);

        // Definir estados finais
        automato.setFinalState(4);

        // Definir transições
        //origem destino simbolo
        automato.setTransition(0, 0, "1"); // De 0 para 0 com '1'
        automato.setTransition(0, 0, "2"); // De 0 para 0 com '2'
        automato.setTransition(0, 1, "0"); // De 0 para 1 com '0'

        automato.setTransition(1, 2, "1"); // De 1 para 2 com '1'
        automato.setTransition(1, 3, "2"); // De 1 para 3 com '2'

        automato.setTransition(2, 2, "0"); // De 2 para 2 com '0'
        automato.setTransition(2, 4, "1"); // De 2 para 4 - final com '1'
        automato.setTransition(2, 4, "2"); // De 1 para 2 com '1'

        automato.setTransition(3, 3, "0"); // De 3 para 3 com '0'
        automato.setTransition(3, 3, "1"); // De 3 para 3 com '1'
        automato.setTransition(3, 4, "2"); // De 3 para 4 com '2'

        automato.setTransition(4, 4, "0"); // De 4 para 4 com '0'
        automato.setTransition(4, 4, "1"); // De 4 para 4 com '1'
        automato.setTransition(4, 4, "2"); // De 4 para 4 com '2'

        return automato;
    }
    public static void transitionShow(String symbol, Automata automato) {
        
    }

    public static void finalStateOfAutomata(String symbol, Automata myAutomata, int origin) {
        
    }
}
