package regapp.web.mbeans;

import org.modelmapper.ModelMapper;
import regapp.domain.models.view.EmployeeListViewModel;
import regapp.service.EmployeeService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class EmployeeListBean {

    private List<EmployeeListViewModel> employees;
    private BigDecimal totalSalary;
    private BigDecimal avgSalary;

    private EmployeeService employeeService;
    private ModelMapper modelMapper;

    public EmployeeListBean() {
    }

    @Inject
    public EmployeeListBean(EmployeeService employeeService, ModelMapper modelMapper) {
        this.employeeService = employeeService;
        this.modelMapper = modelMapper;
        this.employees = this.employeeService.findAllEmployees()
                .stream()
                .map(e -> this.modelMapper.map(e, EmployeeListViewModel.class))
                .collect(Collectors.toList());

        BigDecimal sum = employees
                .stream()
                .map(e -> e.getSalary())
                .reduce(BigDecimal::add)
                .get();

        setTotalSalary(sum);
        setAvgSalary(sum.divide(new BigDecimal(this.employees.size()), 2));
    }

    public List<EmployeeListViewModel> getEmployees() {
        return this.employees;
    }

    public void setEmployees(List<EmployeeListViewModel> employees) {
        this.employees = employees;
    }


    public BigDecimal getTotalSalary() {
        return this.totalSalary;
    }

    public void setTotalSalary(BigDecimal totalSalary) {
        this.totalSalary = totalSalary;
    }

    public BigDecimal getAvgSalary() {
        return this.avgSalary;
    }

    public void setAvgSalary(BigDecimal avgSalary) {
        this.avgSalary = avgSalary;
    }
}