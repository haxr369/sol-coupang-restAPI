//package com.example.solcoupang.user.model;
//
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import lombok.extern.slf4j.Slf4j;
//
//
//public interface KafkaListener<T> {
//    void consume(T consumed);
//    KafkaType getType();
//    /**
//     * ㅋㅏ프카를 새롭게 발생하면
//     * 카프카 컨슈머를 새롭게 만들어서 Bean이 떴을 때 로그가 찍혀야하는 것
//     *
//     */
//}
//
//
//
//
//@AllArgsConstructor
//@NoArgsConstructor
//@Setter
//@Getter
//class UserKafkaBody{
//    private String name;
//    private int age;
//}
//
//@Slf4j
//class PaymentKafkaListenser implements KafkaListener<UserKafkaBody>{
//
//    // Bean으로 떴을 때 로깅
//    // 공통됐지만 매번 동일한 로직을 수행한다면 굳이 매번 구현할 필요없다. 그래서 추상 클래스를 사용해서 미리 구현해주자.
//    // 어떻게 "UserKafkaBody" 대신에 Kafka에 접근하는 객체의 이름을 넣을 수 있을까?
//    public void instantiated(){
//        log.info("UserKafkaBody bean is creadted");
//    }
//
//    @Override
//    public void consume(UserKafkaBody consumed) {
//        // 중앙화하는 로직 구현
//        log.info("cunsumed {}",consumed);
//    }
//
//    @Override
//    public KafkaType getType() {
//        return KafkaType.USER_KAFKA;
//    }
//}
//
//
//enum KafkaType{
//    USER_KAFKA("user_kafka", TypeEnumValue.HELLO_VALUE),
//    PAYMENT_KAFKA("payment_kafka", TypeEnumValue.WORLD_VALUE);
//    private String type;
//    private TypeEnumValue typeEnumValue;
//    // enum의 다중화가 가능하다!!
//    KafkaType(String type, TypeEnumValue typeEnumValue){
//        this.type = type;
//        this.typeEnumValue = typeEnumValue;
//    }
//}
//
//
//enum TypeEnumValue{
//    HELLO_VALUE,
//    WORLD_VALUE;
//
//}
