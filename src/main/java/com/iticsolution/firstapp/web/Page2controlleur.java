package com.iticsolution.firstapp.web;

import com.iticsolution.firstapp.dao.Personne;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class Page2controlleur {
   private static List<Personne> listePersonnes=new ArrayList<>();
    static {
        Personne p1=new Personne("Mohamed","Amin");
        Personne p2=new Personne("Rachid","Khalidi");
        Personne p3=new Personne("Khadija","Ibahimi");
        listePersonnes.add(p1);
        listePersonnes.add(p2);
        listePersonnes.add(p3);
    }
@GetMapping("/page2")
public String getPage2(Model model){
    String message="Hello page 2 from server";
    List<String> listMessages=new ArrayList<>();
    listMessages.add("Message1");
    listMessages.add("Message2");
    listMessages.add("Message3");
    listMessages.add("Message4");
    model.addAttribute("message",message);
    model.addAttribute("messages",listMessages);
    return "page2";
}

    @GetMapping("/listPersonne")
    public String getPersonnePage(Model model){

        model.addAttribute("personnes",listePersonnes);
        return "listPersonne";
    }

    @GetMapping("/addPersonne")
    public String getAddPersonnePage(Model model){
Personne personneForm=new Personne();
        model.addAttribute("personneForm",personneForm);
        return "addPersonne";
    }

    //@PostMapping("/addPersonne")
    @RequestMapping(value = {"/addPersonne"},method = RequestMethod.POST)
    public String savePersonne(Model model,
                               @ModelAttribute("personneForm")Personne personneForm){
    String nom=personneForm.getNom();
    String prenom=personneForm.getPrenom();
    if(nom!= null && nom.length()>0 && prenom!=null && prenom.length()>0){
        Personne newPersonne=new Personne(nom,prenom);
        listePersonnes.add(newPersonne);
        return "redirect:/listPersonne";
    }
    model.addAttribute("errorMessage","Il faut saisir toutes les information");
       return "addPersonne";
    }
}
