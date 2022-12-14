package by.tms.service;

import by.tms.dao.OperationDao;
import by.tms.entity.Operation;
import by.tms.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CalculatorService {
    @Autowired
    OperationDao operationDao;

    public Optional<Operation> calculate(Operation operation, User user) {
        switch (operation.getOperation()) {
            case "sum": {
                operation.setResult(operation.getNum1() + operation.getNum2());
                break;
            }
            case "diff": {
                operation.setResult(operation.getNum1() - operation.getNum2());
                break;
            }
            case "mul": {
                operation.setResult(operation.getNum1() * operation.getNum2());
                break;
            }
            case "div": {
                operation.setResult(operation.getNum1() / operation.getNum2());
                break;
            }
        }
        operation.setUsername(user.getUsername());
        operation.setName(user.getName());
        operationDao.save(operation);
        return Optional.of(operation);
    }


    public List<Operation> findAllOperationByUsername(String username) {
        return operationDao.findAllOperationByUsername(username);
    }
}
