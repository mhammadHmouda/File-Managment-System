package app.filerepository.controller;

import app.filerepository.request.ClassifyDateRequest;
import app.filerepository.response.ResponseMessage;
import app.filerepository.services.classify.AClassify;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static app.filerepository.services.exportservice.enums.ExportType.*;

@RestController
public class Classify {

    @Autowired
    @Qualifier("local")
    private AClassify<ClassifyDateRequest> classifyDateService;
    @Autowired
    @Qualifier("s1")
    private AClassify<String> classifyTypeService;
    @Autowired
    @Qualifier("s2")
    private AClassify<Integer> classifySizeService;

    @Autowired
    @Qualifier("s3")
    private AClassify<String> classifyNameService;

    private static final Logger logger = LoggerFactory.getLogger(Classify.class);

    @GetMapping("/classifyByType/{type}")
    public ResponseMessage classifyByType(@PathVariable String type) {
        logger.info("Create classifyByType response");
        return classifyTypeService.classify(type, FILE_TYPE.name());
    }

    @GetMapping("/classifyBySize/{size}")
    public ResponseMessage classifyBySize(@PathVariable Integer size){
        logger.info("Create classifyBySize response");
        return classifySizeService.classify(size, SIZE.name());
    }

    @GetMapping("/classifyByDate")
    public ResponseMessage classifyByDate(@RequestBody ClassifyDateRequest classifyDateRequest){
        logger.info("Create classifyByDate response");
        return classifyDateService.classify(classifyDateRequest, DATE.name());
    }

    @GetMapping("/classifyByName/")
    public ResponseMessage classifyByName(@PathVariable String fileName){
        logger.info("Create classifyByDate response");
        return classifyNameService.classify(fileName, FILE_NAME.name());
    }
}
