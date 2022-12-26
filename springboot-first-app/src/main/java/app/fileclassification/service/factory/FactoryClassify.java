package app.fileclassification.service.factory;

import app.exception.ClassifyMethodNotFound;
import app.fileclassification.request.ClassifyDateRequest;
import app.fileclassification.service.abs.AClassify;
import app.response.ResponseMessage;
import app.userauth.config.TokenProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

import static app.fileclassification.constant.ClassifyEnum.*;
import static app.fileclassification.constant.ClassifyException.*;
import static app.fileclassification.constant.ImplementType.*;
import static app.userauth.role.RoleEnum.ADMIN;
import static app.userauth.role.RoleEnum.STAFF;


@Service
public class FactoryClassify <T> {

    @Autowired
    @Qualifier(IMPLEMENTATION_DATE_CLASS)
    private AClassify<ClassifyDateRequest> classifyDateService;
    @Autowired
    @Qualifier(IMPLEMENTATION_TYPE_CLASS)
    private AClassify<String> classifyTypeService;
    @Autowired
    @Qualifier(IMPLEMENTATION_SIZE_CLASS)
    private AClassify<Integer> classifySizeService;
    @Autowired
    @Qualifier(IMPLEMENTATION_NAME_CLASS)
    private AClassify<String> classifyNameService;

    @Autowired
    TokenProvider jwtTokenUtils;

    private static final Logger logger = LoggerFactory.getLogger(FactoryClassify.class);

    public ResponseMessage factory(T value, String name, HttpServletRequest request) throws ClassifyMethodNotFound {

        String role =  jwtTokenUtils.getRole(request);
        if(!role.equalsIgnoreCase(ADMIN.name()) && !role.equalsIgnoreCase(STAFF.name())) {
            return ResponseMessage.getInstance(NO_PERMISSION);
        }

        if(name.equalsIgnoreCase(CLASSIFY_OF_DATE.name())){
            return classifyDateService.classify((ClassifyDateRequest) value, name);
        }
        else if(name.equalsIgnoreCase(CLASSIFY_OF_TYPE.name())){
            return classifyTypeService.classify((String) value, name);
        }
        else if(name.equalsIgnoreCase(CLASSIFY_OF_NAME.name())){
            return classifyNameService.classify((String) value, name);
        }
        else if(name.equalsIgnoreCase(CLASSIFY_OF_SIZE.name())){
            return classifySizeService.classify((Integer) value, name);
        }

        throw new ClassifyMethodNotFound(NOT_ALLOWED);
    }
}
