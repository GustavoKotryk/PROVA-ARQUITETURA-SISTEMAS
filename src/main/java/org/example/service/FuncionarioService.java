package org.example.service;

import org.example.model.Funcionario;

import java.util.ArrayList;
import java.util.List;

public class FuncionarioService {

    private List<Funcionario> funcionarios = new ArrayList<>();

    public void contratar(String nome, String cargo, double salario){
        funcionarios.add(new Funcionario(nome, cargo, salario));
    }

    public void listar(){
        for(int i = 0; i < funcionarios.size(); i++){
            Funcionario func = funcionarios.get(i);
            System.out.println(i + ": " + func.getNome() + " (" + func.getCargo() + " )");
        }
    }

    public void aumentarSalarioPorNome(String nome, double porcentagem){
        for(Funcionario func : funcionarios){
            if(func.getNome().equalsIgnoreCase(nome)){
                double novo = func.getSalario() * (1 + porcentagem/100);
                func.setSalario(novo);
                System.out.println("Sálario atualizado!");
                return;
            }
        }
        System.out.println("Funcionario não encontrado");
    }
}
