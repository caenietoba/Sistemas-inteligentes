from board import NOT_VISITED, VISITED, MS_PACMAN, WALL

def left(board):
    return movePacman(board, 0, -1, 'l', board.last_movement)

def rigth(board):
    return movePacman(board, 0, 1, 'r', board.last_movement)

def up(board):
    return movePacman(board, -1, 0, 'u', board.last_movement)

def down(board):
    return movePacman(board, 1, 0, 'd', board.last_movement)

def movePacman(board, row_incr, col_incr, movement, last_movement):
    (row, col) = board.ms_pacman

    new_row = row + row_incr
    new_col = col + col_incr

    # Si se puede pasar al otro lado del mapa
    if new_col == -1:
        new_col = len(board.board[0]) - 1
    if new_row == -1:
        new_row = len(board.board) - 1
    if new_col == len(board.board[0]):
        new_col = 0
    if new_row == len(board.board):
        new_row = 0

    # Si se encuntra con un muro o para que no se pueda devolver
    # y se quede en un bucle
    if board.board[new_row][new_col] == WALL:
        return None
    if last_movement == 'u' and movement == 'd':
        return None
    if last_movement == 'd' and movement == 'u':
        return None
    if last_movement == 'r' and movement == 'l':
        return None
    if last_movement == 'l' and movement == 'r': 
        return None
    
    # Actualiza el mapa y todos sus valores
    board.last_movement = movement
    if board.board[new_row][new_col] == NOT_VISITED:
        board.num_not_visited -= 1
    board.board[row][col] = VISITED
    board.board[new_row][new_col] = MS_PACMAN
    board.ms_pacman = (new_row, new_col)

    # Frena a ms pacman en caso de que hayan dos o mas caminos que puede coger
    # distintos del que acaba de usar
    if movement == 'u' or movement == 'd':
        if not isWall(board, new_row, new_col -1 ) or not isWall(board, new_row, new_col + 1):
            return board
    if movement == 'l' or movement == 'r':
        if not isWall(board, new_row - 1 , new_col) or not isWall(board, new_row + 1, new_col):
            return board

    return movePacman(board, row_incr, col_incr, movement, last_movement)


def isWall(board, row, col):
    if board.board[row][col] == WALL:
        return True
    return False


moves = [left, rigth, up, down]