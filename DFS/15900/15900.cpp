#include <iostream>
#include <vector>
#define MAX 500001
using namespace std;

vector<int> v[MAX];
bool visited[MAX];
int answer = 0;

void dfs(int node, int count) {


  for (int i = 0; i < v[node].size(); i++) {
    int next = v[node][i];
    if (visited[next]) continue;

    visited[next] = true;
    dfs(v[node][i], count + 1);
    visited[next] = false;
  }

  if (node != 1 && v[node].size() == 1) {
    if (count%2==1) {
      answer++;
    }
  }
}

int main() {
  int N;
  cin >> N;

  for (int i = 0; i < N - 1; i++) {
    int a, b;
    cin >> a >> b;

    v[a].push_back(b);
    v[b].push_back(a);
  }

  visited[1] = true;
  dfs(1, 0);

  // 홀수인 개수가 홀수개여야 함.
  if (answer%2==1 ) {
    cout << "Yes\n";
  } else {
    cout << "No\n";
  }
}
/*

  1
  2
3   4

           1
    4      8     3
  6  7           2
  5 

  1
  2
*/