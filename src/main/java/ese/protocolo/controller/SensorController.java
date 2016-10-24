package ese.protocolo.controller;

import ese.protocolo.model.Variable;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by francisco on 10/22/16.
 */
@RestController
public class SensorController {

    private List<Variable> list = new ArrayList<>();

    //@CrossOrigin(origins = "*")
    @RequestMapping(method = RequestMethod.POST, path = "/values")
    public ResponseEntity<Double> add(@RequestBody Double value){
        Variable e = new Variable(LocalDateTime.now().toString(), value);
        list.add(e);
        return ResponseEntity.ok(e.getValue());
    }

    //@CrossOrigin(origins = "*")
    @RequestMapping(method = RequestMethod.GET, path = "/values")
    public ResponseEntity<List<Variable>> list(){
        List<Variable> newList = new ArrayList<>();
        for(int i = 0; i < list.size(); i++){
            newList.add(list.get(list.size() - 1 - i));
        }
        return ResponseEntity.ok(newList);
    }

}
