package ca.jrvs.apps.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class LambdaStreamImp implements LambdaStreamExc {

    @Override
    public Stream<String> createStrStream(String... strings) {
        return Stream.of(strings);
    }

    @Override
    public Stream<String> toUpperCase(String... strings) {
        return createStrStream(strings).map(String::toUpperCase);
    }

    @Override
    public Stream<String> filter(Stream<String> stringStream, String pattern) {
        return stringStream.filter(s -> s.contains(pattern));
    }

    @Override
    public IntStream createIntStream(int[] arr) {
        return IntStream.of(arr);
    }

    @Override
    public <E> List<E> toList(Stream<E> stream) {
        return stream.collect(Collectors.toList());
    }

    @Override
    public List<Integer> toList(IntStream intStream) {
        return intStream.boxed().collect(Collectors.toList());
    }

    @Override
    public IntStream createIntStream(int start, int end) {
        return IntStream.rangeClosed(start, end);
    }

    @Override
    public DoubleStream squareRootIntStream(IntStream intStream) {
        return intStream.asDoubleStream().map(Math::sqrt);
    }

    @Override
    public IntStream getOdd(IntStream intStream) {
        return intStream.filter(n -> n % 2 != 0);
    }

    @Override
    public Consumer<String> getLambdaPrinter(String prefix, String suffix) {
        return message -> System.out.println(prefix + message + suffix);
    }

    @Override
    public void printMessages(String[] messages, Consumer<String> printer) {
        Stream.of(messages).forEach(printer);
    }

    @Override
    public void printOdd(IntStream intStream, Consumer<String> printer) {
        getOdd(intStream).mapToObj(String::valueOf).forEach(printer);
    }

    @Override
    public Stream<Integer> flatNestedInt(Stream<List<Integer>> ints) {
        return ints.flatMap(list -> list.stream().map(n -> n * n));
    }

    public static void main(String[] args) {
        LambdaStreamExc lambdaStreamExc = new LambdaStreamImp();
        Stream<String> newStream = lambdaStreamExc.createStrStream("qwerty", "ytrewq", "helloworld");
        newStream = lambdaStreamExc.toUpperCase("qwerty", "ytrewq", "helloworld");
        Stream<String> filteredStream = lambdaStreamExc.filter(newStream, "QWERTY");
        IntStream intStream = lambdaStreamExc.createIntStream(new int[]{1,45,5,67});
        List<String> list = lambdaStreamExc.toList(filteredStream);
        List<Integer> list1 = lambdaStreamExc.toList(intStream);
        for(Integer i : list1){
            System.out.println(i);
        }
        IntStream intStream1 = lambdaStreamExc.createIntStream(3,6);
        DoubleStream doubleStream = lambdaStreamExc.squareRootIntStream(intStream1);
        IntStream intStream2 = lambdaStreamExc.getOdd(intStream1);
        Consumer<String> printer = lambdaStreamExc.getLambdaPrinter("1", "2");
        lambdaStreamExc.printMessages(new String[]{"qwe", "ewq"}, printer);
        lambdaStreamExc.printOdd(intStream1, printer);
        intStream2.forEach(System.out::println);

        List<Integer> even = Arrays.asList( 2, 4, 6, 8, 10);
        List<Integer> odd = Arrays.asList( 3, 5, 7, 9, 11);
        List<Integer> primes = Arrays.asList(17, 19, 23, 29, 31);
        List<List<Integer>> listOfNumbers = new ArrayList<>();
        listOfNumbers.add(even);
        listOfNumbers.add(odd);
        listOfNumbers.add(primes);
        Stream<List<Integer>> streamIfLists = listOfNumbers.stream();
        Stream<Integer> stream3 = lambdaStreamExc.flatNestedInt(streamIfLists);
        stream3.forEach(System.out::println);
    }
}
