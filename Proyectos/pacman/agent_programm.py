from board import Board

from moves import moves
from heuristic import Heuristic

from Queue import PriorityQueue
import copy

class AgentProgramm:

    def __init__(self, board):
        self.board = Board(board)

    def a_star(self):

        heuristic = Heuristic()
        moves_solution = []
        print

        while not self.board.num_not_visited == 0 and not self.board == None:
            queue = PriorityQueue()
            for move in moves:
                aux_board = move(copy.deepcopy(self.board))
                if aux_board == None:
                    continue

                aux_board.priority = heuristic.heuristicA(aux_board)
                queue.put(aux_board)

            self.board = queue.get()
            # self.board.printBoard()
            moves_solution.append(self.board.last_movement)
            # queue.queue.clear()

        print('finish with a total of {} moves').format(len(moves_solution))
    
