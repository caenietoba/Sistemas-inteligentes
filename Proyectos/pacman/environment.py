from agent_programm import AgentProgramm

from find_map import get_board

def run_no_recognition(board):
    agent = AgentProgramm(board)
    agent.a_star()

def run_recognition(file):
    board = get_board(file)
    #agent = AgentProgramm(board)
    #agent.a_star()