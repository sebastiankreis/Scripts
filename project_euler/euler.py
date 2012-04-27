prime_set = set()


## problem 7
def product(xs):
    return reduce(lambda u, v: int(u) * int(v), xs)


def largest_product(xs):
    prod_gen = (product(xs[i:i + 5]) for i in xrange(len(xs)))
    return max(map(int, prod_gen))


## Problem 8
def isPrime(n):
    if n in prime_set:
        return True

    return not (n < 2 or any(n % x == 0 for x in xrange(2, int(n ** 0.5) + 1)))


## Problem 10
def sum_primes_below(n):
    curr_sum = 0
    for i in xrange(n):
        if isPrime(i):
            prime_set.add(i)
            curr_sum += i

    return curr_sum
