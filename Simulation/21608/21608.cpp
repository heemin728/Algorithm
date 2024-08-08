#include <algorithm>
#include <iostream>
#include <vector>
#include<math.h>
#define MAX 21
using namespace std;

int arr[MAX][MAX];
int dist[MAX][MAX];
vector<int> likes[MAX*MAX];
int N;
int students;
int emptySpace;
int dx[4] = {1, -1, 0, 0};
int dy[4] = {0, 0, 1, -1};

bool isInRange(int i, int j) { return i > 0 && j > 0 && i <= N && j <= N; }


/*
*  좋아하는 학생이 인접한 칸에 가장 많은 칸의 모든 좌표를  pair로 반환 
*  좋아하는 학생이 인접한 칸에 가장 많은 칸의 인접한 빈칸 최대의 개수를 emptySpace 전역변수에 전달
*/
vector<pair<int, int>> countMaxLikes (int student, vector<int> like) {
  vector<pair<int, int>> ret;

  int tMax = 0;

  for (int i = 1; i <= N; i++) {
    for (int j = 1; j <= N; j++) {
      // 이미 차지된 공간은 고려하지 않음
      if (arr[i][j] != 0) {
        continue;
      }

      int cnt = 0;
      int tEmpty = 0;

      for (int k = 0; k < 4; k++) {
        int ni = i + dx[k];
        int nj = j + dy[k];

        if (!isInRange(ni, nj)) continue;

        // 인접 빈칸 개수
        if (arr[ni][nj] == 0) tEmpty++;
        
        if (find(like.begin(), like.end(), arr[ni][nj]) != like.end()) {
          cnt++;
        }
      }

      if (cnt > tMax) {
        ret.clear();
        ret.push_back({i, j});
        emptySpace = tEmpty;
        tMax = cnt;
      } else if (cnt == tMax) {
        emptySpace = max(emptySpace,tEmpty);
        ret.push_back({i, j});
      }
    }
  }
  return ret;
}

/**
 *  좋아하는 학생이 인접한 칸에 가장 많은 칸 중에서 
 *  인접한 빈 칸이 가장 많은 칸 중
 *  가장 빠른 행, 열에 배정 후 리턴
 */
void countMaxSpaces(int student, vector<pair<int, int>> res) {

  for (int i = 0; i < res.size(); i++) {

    int x = res[i].first;
    int y = res[i].second;

    int cnt = 0;

    for (int k = 0; k < 4; k++) {
      int nx = x + dx[k];
      int ny = y + dy[k];

      if (!isInRange(nx, ny)) continue;

      if (arr[nx][ny] == 0) cnt++;
    }

    if (emptySpace == cnt) {
      arr[x][y] = student;
      return;
    }
  }
}

int main() {
  cin >> N;
  students = N * N;

  for (int i = 1; i <= students; i++) {
    int student;
    cin >> student;
    for (int j = 1; j <= 4; j++) {
      int l;
      cin >> l;
      likes[student].push_back(l);
    }

    vector<pair<int, int>> res = countMaxLikes(student, likes[student]);
    countMaxSpaces(student,res);
    emptySpace = 0; // 빈 칸 최댓값 초기화

  }

  // 점수 계산하기
  int answer = 0;

  for (int i = 1; i <= N; i++) {
    for (int j = 1; j <= N; j++) {
      int s = arr[i][j];

      int cnt = 0;
      for (int k = 0; k < 4; k++) {
        int ni = i + dx[k];
        int nj = j + dy[k];

        if (!isInRange(ni, nj)) continue;

        if (find(likes[s].begin(), likes[s].end(), arr[ni][nj]) != likes[s].end()) {
          cnt++;
        }
      }

      answer += pow(10,cnt-1);
    }
  }
  cout << answer << "\n";
}