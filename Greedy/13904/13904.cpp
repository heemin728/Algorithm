#include <algorithm>
#include <iostream>
#include <queue>
#define MAX 1001

using namespace std;

struct compare {
  bool operator()(pair<int, int>& a, pair<int, int>& b) {
    if (a.second < b.second)
      return true;
    else if (a.second > b.second)
      return false;
    if (a.first < b.first) return true;
    return false;
  }
};

int main() {
  int N;
  cin >> N;

  priority_queue<pair<int, int>, vector<pair<int, int>>, compare> q;

  for (int i = 0; i < N; i++) {
    int d, w;
    cin >> d >> w;

    q.push({d, w});
  }

  int getPoint = 0;

  bool days[MAX] = {
      false,
  };

  while (!q.empty()) {
    int day = q.top().first;
    int point = q.top().second;
    q.pop();

    for (int i = day; i >= 1; i--) {
      if (!days[i]) {
        days[i] = true;
        getPoint += point;
        break;
      }
    }
  }
  cout << getPoint << "\n";
}