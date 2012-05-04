#!/usr/bin/env python
import itertools


def generate_dictionary(filename="words.txt"):
    scores_dict = {}
    # the with statement uses a context manager to always close the
    # file object even when there's an exception
    with open(filename, 'r') as dictionary:
        for line in dictionary:
            line = line.strip().lower()

            if not line or len(line) > 7:
                continue

            key = ''.join(sorted(line))

            if key in scores_dict:
                continue

            value = score_word(key), line
            scores_dict[key] = value

    return scores_dict


def score_word(word):
    return sum(score[c] for c in word)


def choose_optimal_word(scores_dict, tiles):
    best_word = ""
    current_max = 0

    for i in range(len(tiles) + 1):
        for choice in itertools.combinations(tiles, i):
            choice = ''.join(sorted(choice))

            if choice not in scores_dict:
                continue

            if current_max < scores_dict[choice]:
                current_max = scores_dict[choice][0]
                best_word = scores_dict[choice][1]

    return best_word, current_max


def main():
    scores_dict = generate_dictionary()
    tiles = raw_input("Enter your tiles separated by spaces\n--> ")
    tiles = [t.lower() for t in tiles.strip().split()]
    best_choice, points = choose_optimal_word(scores_dict, tiles)
    print("Best choice is {} with {} points".format(best_choice, points))


score = {"a": 1, "c": 3, "b": 3, "e": 1, "d": 2, "g": 2,
         "f": 4, "i": 1, "h": 4, "k": 5, "j": 8, "m": 3,
         "l": 1, "o": 1, "n": 1, "q": 10, "p": 3, "s": 1,
         "r": 1, "u": 1, "t": 1, "w": 4, "v": 4, "y": 4,
         "x": 8, "z": 10}


if __name__ == '__main__':
    main()
