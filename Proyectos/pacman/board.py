import copy

from functools import total_ordering

VISITED = 1
NOT_VISITED = 0
MS_PACMAN = 2
WALL = 3

@total_ordering
class Board:

    def __init__(self, board, copy=False):
        self.board = board
        (self.num_not_visited, self.ms_pacman) = self.__find_data()
        self.last_movement = ''
        self.priority = 0

    def __find_data(self):
        pos_x = 0; pos_y = 0; num_not_visited = 0; i = 0
        for i in range(len(self.board)):
            for j in range(len(self.board[0])):
                if self.board[i][j] == NOT_VISITED:
                    num_not_visited += 1
                if self.board[i][j] == MS_PACMAN:
                    pos_x = i
                    pos_y = j

        return (num_not_visited, (pos_x, pos_y))

    def finished(self):
        return self.num_not_visited == 0

    def printBoard(self):
        print('\n')
        print("The movement was: " + self.last_movement, self.num_not_visited)
        for _list in self.board:
            print([value for value in _list])
        print('\n')

    def __eq__(self, other):
        if isinstance(other, Board):
            return self.priority == other.priority
        return 0

    def __lt__(self, other):
        if isinstance(other, Board):
            return self.priority < other.priority
        return 0
    

