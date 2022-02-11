1.本demo基于springboot，模拟controller层--全局异常处理：请求参数异常(参数校验)+自定义异常+其他异常

2.进行异常处理时，对异常进行归类：
对于异常处理的基本逻辑为：
（1）对请求参数校验异常单独进行全局统一处理
（2）对自定义的异常单独进行全局统一处理
（3）对项目中未知或不好归类的异常单独进行全局统一处理

3.一般entity实体类的参数校验，异常为两种：
（1）BindException（@Validated @Valid仅对于form表单提交有效即url入参，对于以json格式提交将会失效）
   当定义了JSR303校验器后，校验不通过都会产生一个BindEcxeption

（2） MethodArgumentNotValidException（@Validated @Valid 前端提交的方式为json格式有效，出现异常时会被该异常类处理）
  【前两种可以合并在一个捕捉异常的方法中，常用于前端提交给后端的数据（表单和json格式）】

4.没有entity实体类，只有单独属性形参做方法参数的参数校验，异常：
（1）ConstraintViolationException（@NotBlank @NotNull @NotEmpty）格式校验异常
  第三种常用于url传参中的参数格式校验，当没有实体对象入参，只是属性入参时，必须在每个属性前加上校验注解