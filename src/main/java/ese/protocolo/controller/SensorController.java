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

    @RequestMapping(method = RequestMethod.POST, path = "/values")
    public ResponseEntity<Integer> add(@RequestBody Integer value, @RequestBody Integer sensorId){
        Variable e = new Variable(LocalDateTime.now().toString(), value, sensorId);
        list.add(e);
        return ResponseEntity.ok(e.getValue());
    }

    @RequestMapping(method = RequestMethod.GET, path = "/values", params = {"val", "sensorId"})
    public ResponseEntity<Integer> getAdd(@RequestParam("val") Integer value, @RequestParam("sensorId") Integer sensorId){
        Variable e = new Variable(LocalDateTime.now().toString(), value, sensorId);
        list.add(e);
        return ResponseEntity.ok(e.getValue());
    }

    @RequestMapping(method = RequestMethod.GET, path = "/values")
    public ResponseEntity<List<Variable>> list(){
        List<Variable> newList = new ArrayList<>();
        for(int i = 0; i < list.size(); i++){
            newList.add(list.get(list.size() - 1 - i));
        }
        return ResponseEntity.ok(newList);
    }

}
