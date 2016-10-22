package ese.protocolo.controller;

import ese.protocolo.model.Variable;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by francisco on 10/22/16.
 */
@RestController
public class SensorController {

    private List<Variable> list = new ArrayList<>();

    @RequestMapping(method = RequestMethod.POST, path = "/values")
    public ResponseEntity<Integer> add(@RequestBody Integer value){
        Variable e = new Variable(LocalDateTime.now(), value);
        list.add(e);
        return ResponseEntity.ok(e.getValue());
    }

    @RequestMapping(method = RequestMethod.GET, path = "/values")
    public ResponseEntity<List<Variable>> list(){
        return ResponseEntity.ok(list);
    }

}
