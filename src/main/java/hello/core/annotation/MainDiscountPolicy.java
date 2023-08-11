package hello.core.annotation;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.annotation.security.RunAs;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Qualifier("mainDiscountPolicy")
public @interface MainDiscountPolicy {

}
